package com.dreamfirestudios.dreamgamecore.DreamfireVariableTest;

import com.dreamfirestudios.dreamCore.DreamfirePersistentData.PersistentDataTypes;
import com.dreamfirestudios.dreamCore.DreamfireVariable.DreamfireVariableTest;
import com.dreamfirestudios.dreamgamecore.Enum.DreamfireGameCorePermission;

import java.util.ArrayList;
import java.util.List;

public class DreamfireGameCorePermissionVariableTest implements DreamfireVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }

    @Override
    public boolean IsType(Object variable) {
        try{
            var test = DreamfireGameCorePermission.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(DreamfireGameCorePermission.class);
        data.add(DreamfireGameCorePermission[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return DreamfireGameCorePermission.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return DreamfireGameCorePermission.ENABLE_SYSTEM; }

    @Override
    public List<String> TabData(List<String> list, String s) {
        var data = new ArrayList<String>();
        for(var x : DreamfireGameCorePermission.values()) if(x.name().contains(s)) data.add(x.name());
        return data;
    }
}
