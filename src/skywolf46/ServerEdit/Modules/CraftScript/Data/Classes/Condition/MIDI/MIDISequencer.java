package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI;

import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

public class MIDISequencer extends CraftScriptClass {
    private Sequencer seq;

    {
        try {
            seq = MidiSystem.getSequencer();
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            if( synthesizer.getDefaultSoundbank() == null )
            {
                // HARDWARE SYNTHESIZER
                seq.getTransmitter().setReceiver(
                        MidiSystem.getReceiver() );
            }
            else
            {
                // SOFTWARE SYNTHESIZER
                seq.getTransmitter().setReceiver(
                        synthesizer.getReceiver() );
            }
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getClassName() {
        return "Sequencer";
    }

    @Override
    public String getClassPath() {
        return "native.midiplayer";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        return this;
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s,i,c) -> {
            if(s.equalsIgnoreCase("sequencer"))
                return new MIDISequencer();
            return null;
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }

    public Sequencer getSequencer() {
        return seq;
    }
}
