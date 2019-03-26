package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.DoubleClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.NumberClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.IllegalParameterException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MIDIVolume extends CraftScriptClass {
    private CraftScriptClass cl;


    @Override
    public String getClassName() {
        return "player";
    }

    @Override
    public String getClassPath() {
        return "native.midi";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {
        if (cl.length() < 2)
            throw new IllegalParameterException();
        this.cl = cl.get(2);
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {

        if (!(st.get(0) instanceof MIDISequencer))
            throw new IllegalStateException("First parameter is not MIDISequencer.");
        if (!(cl instanceof NumberClass))
            throw new IllegalStateException("Second parameter is not Number.");
        Sequencer seq = ((MIDISequencer) st.get(0)).getSequencer();

        Synthesizer synthesizer = null;



        try {
            synthesizer = (Synthesizer) MidiSystem.getSynthesizer();
            MidiChannel[] channels = synthesizer.getChannels();

            // gain is a value between 0 and 1 (loudest)
            double gain = Math.max(0, Math.min(1, ((NumberClass) cl).getValue().doubleValue() / 100));
            System.out.println("Gain:" + gain);
            System.out.println(synthesizer.getDefaultSoundbank() );
            if (synthesizer.getDefaultSoundbank() != null) {
                ShortMessage volumeMessage = new ShortMessage();
                for (int i = 0; i < 16; i++) {
                    volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE,
                            i, 7, (int) (gain * 127.0));
                    MidiSystem.getReceiver().send(volumeMessage, -1);
                }
            } else {
                for (int i = 0; i < channels.length; i++) {
                    channels[i].controlChange(7, (int) (gain * 127.0));
                }
            }
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        return st.get(0);
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if (!s.equals("volume"))
                return null;
            if (i != 1)
                throw new IllegalParameterException();
            return new MIDIVolume();
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
