package com.example.gamephone;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

interface GamePermissionAccessible{
}
public  class GameSharedPreferences implements GamePermissionAccessible {

    public interface GAME_KEYS{
        public final static String SP_PLAYER_A_TEMPORARY_SCORE = "SP_PLAYER_A_TEMPORARY_SCORE";
        public final static String SP_PLAYER_B_TEMPORARY_SCORE = "SP_PLAYER_B_TEMPORARY_SCORE";
        public final static String SP_PLAYER_A_TEMPORARY_CHARACTER_SELECTION = "SP_PLAYER_A_TEMPORARY_CHARACTER_SELECTION";
        public final static String SP_PLAYER_B_TEMPORARY_CHARACTER_SELECTION = "SP_PLAYER_B_TEMPORARY_CHARACTER_SELECTION";
        public final static String SP_LAST_GAME = "SP_LAST_GAME";
    }
    private static GameSharedPreferences instance = null;
    private SharedPreferences _sp;
    private Context _context_ref;
    private GameSharedPreferences(Context context)
    {
        _context_ref=context;
        _sp=_context_ref.getApplicationContext().getSharedPreferences("sp_name", Context.MODE_PRIVATE);
    }

    public static GameSharedPreferences  getGameSharedPreferences(Context context)
    {
        if(context instanceof GamePermissionAccessible)
        {
            if(instance == null)
            {
                instance=new GameSharedPreferences(context);
            }
            return instance;

        }else
        {
            return null;
        }

    }

    public void putBoolean(String KEY, boolean value) {
        _sp.edit().putBoolean(KEY, value).apply();
    }

    public void putString(String KEY, String value) {
        _sp.edit().putString(KEY, value).apply();
    }

    public void putObject(String KEY, Object value) {
        _sp.edit().putString(KEY, new Gson().toJson(value)).apply();
    }

    public void putInt(String KEY, int value) {
        _sp.edit().putInt(KEY, value).apply();
    }

    public void putLong(String KEY, long value) {
        _sp.edit().putLong(KEY, value).apply();
    }

    public void putFloat(String KEY, float value) {
        _sp.edit().putFloat(KEY, value).apply();
    }

    public void putDouble(String KEY, double defValue) {

        putString(KEY, String.valueOf(defValue));
    }

    public boolean getBoolean(String KEY, boolean defvalue) {
        return _sp.getBoolean(KEY, defvalue);
    }

    public String getString(String KEY, String defvalue) {
        return _sp.getString(KEY, defvalue);
    }

    public <T> T getObject(String KEY, Class<T> mModelClass) {
        Object object = null;
        try {
            object = new Gson().fromJson(_sp.getString(KEY, ""), mModelClass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Primitives.wrap(mModelClass).cast(object);

    }

    public int getInt(String KEY, int defValue) {
        return _sp.getInt(KEY, defValue);
    }

    public long getLong(String KEY, long defValue) {
        return _sp.getLong(KEY, defValue);
    }

    public float getFloat(String KEY, float defValue) {
        return _sp.getFloat(KEY, defValue);
    }

    public double getDouble(String KEY, double defValue) {
        return Double.parseDouble(getString(KEY, String.valueOf(defValue)));
    }

    public void removeKey(String KEY) {
        _sp.edit().remove(KEY).apply();
    }

    public boolean contain(String KEY) {
        return _sp.contains(KEY);
    }

    public void registerChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        _sp.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        _sp.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public <T> void putArray(String KEY, ArrayList<T> array) {
        String json = new Gson().toJson(array);
        _sp.edit().putString(KEY, json).apply();
    }

    public <T> ArrayList<T> getArray(String KEY, TypeToken typeToken) {
        // type token == new TypeToken<ArrayList<YOUR_CLASS>>() {}
        ArrayList<T> arr = null;
        try {
            arr = new Gson().fromJson(_sp.getString(KEY, ""), typeToken.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    public <S, T> void putMap(String KEY, HashMap<S, T> map) {
        String json = new Gson().toJson(map);
        _sp.edit().putString(KEY, json).apply();
    }

    public <S, T> HashMap<S, T> getMap(String KEY, TypeToken typeToken) {
        // getMap(MySharedPreferencesV4.KEYS.SP_PLAYLISTS, new TypeToken<HashMap<String, Playlist>>() {});
        // type token == new TypeToken<ArrayList<YOUR_CLASS>>() {}
        HashMap<S, T> map = null;
        try {
            map = new Gson().fromJson(_sp.getString(KEY, ""), typeToken.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }



}
