package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.IllegalParameterException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.File;
import java.io.IOException;

public class MIDIPlayFile extends CraftScriptClass {
    @Override
    public String getClassName() {
        return "start";
    }

    @Override
    public String getClassPath() {
        return "native.midi";
    }

    @Override
    public void applyData(CompileStatus cl, int currentIndex) {

    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        Sequencer seq = ((MIDISequencer) st.get(0)).getSequencer();
        seq.start();
        return st.get(0);
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if (!s.equals("play"))
                return null;
            if (i != 1)
                throw new IllegalParameterException();
            return new MIDIPlayFile();
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
