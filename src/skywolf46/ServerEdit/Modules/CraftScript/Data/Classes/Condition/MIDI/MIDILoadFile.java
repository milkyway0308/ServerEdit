package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Condition.MIDI;

import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Invokers.InvokerScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Native.StringClass;
import skywolf46.ServerEdit.Modules.CraftScript.Data.CompileStatus;
import skywolf46.ServerEdit.Modules.CraftScript.Data.ExecuteState;
import skywolf46.ServerEdit.Modules.CraftScript.Exceptions.IllegalParameterException;
import skywolf46.ServerEdit.Modules.CraftScript.Extension.CraftScriptClass;
import skywolf46.ServerEdit.Modules.CraftScript.Util.ScriptUtil;
import skywolf46.ServerEdit.Modules.CraftScript.Util.TripleFunction;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MIDILoadFile extends CraftScriptClass {
    private File f;


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
        if(cl.length() < 2 || !(cl.get(currentIndex+1) instanceof StringClass))
            throw new IllegalParameterException();
        f = new File(cl.get(currentIndex+1).toString());
    }

    @Override
    public CraftScriptClass process(ExecuteState state, CompileStatus st, int currentIndex) {
        if(!f.exists())
            throw new IllegalStateException("MIDI File not found.");
        if(!(st.get(0) instanceof MIDISequencer))
            throw new IllegalStateException("First parameter is not MIDISequencer.");
        Sequencer seq = ((MIDISequencer)st.get(0)).getSequencer();
        try {
            seq.open();
            seq.setSequence(MidiSystem.getSequence(f));
        } catch (MidiUnavailableException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (InvalidMidiDataException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());

        }
        return st.get(0);
    }

    @Override
    public TripleFunction<String, Integer, CompileStatus, CraftScriptClass> getClassParser() {
        return (s, i, c) -> {
            if(!s.equals("load"))
                return null;
            if(!ScriptUtil.isInstanceOf(c.get(0),MIDISequencer.class))
                return null;
            if (i != 1)
                throw new IllegalParameterException();
            return new MIDILoadFile();
        };
    }

    @Override
    public int processPriority() {
        return 0;
    }
}
