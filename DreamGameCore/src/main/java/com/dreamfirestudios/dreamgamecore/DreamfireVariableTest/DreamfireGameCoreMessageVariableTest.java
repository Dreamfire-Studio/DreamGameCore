package com.dreamfirestudios.dreamgamecore.DreamfireVariableTest;

import com.dreamfirestudios.dreamCore.DreamfirePersistentData.PersistentDataTypes;
import com.dreamfirestudios.dreamCore.DreamfireVariable.DreamfireVariableTest;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCoreMessage;

import java.util.ArrayList;
import java.util.List;

public class DreamfireGameCoreMessageVariableTest implements DreamfireVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }

    @Override
    public boolean IsType(Object variable) {
        try{
            var test = DreamfireGameCoreMessage.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(DreamfireGameCoreMessage.class);
        data.add(DreamfireGameCoreMessage[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return DreamfireGameCoreMessage.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return DreamfireGameCoreMessage.values()[0]; }

    @Override
    public List<String> TabData(List<String> list, String s) {
        var data = new ArrayList<String>();
        for(var x : DreamfireGameCoreMessage.values()) if(x.name().contains(s)) data.add(x.name());
        return data;
    }
}