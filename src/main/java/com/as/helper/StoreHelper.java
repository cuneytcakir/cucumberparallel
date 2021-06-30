package com.as.helper;


import com.google.common.reflect.TypeToken;
import com.as.model.ElementInfo;
import io.cucumber.messages.internal.com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum StoreHelper {
  INSTANCE;
  private static final String DEFAULT_DIRECTORY_PATH = "elementValues";
  ConcurrentMap<String, Object> elementMapList;

  StoreHelper(){
    initMap(getFileList());
  }

  private void initMap(File[] fileList){
    elementMapList = new ConcurrentHashMap<>();
    Type elementType = new TypeToken<List<ElementInfo>>() {
    }.getType();
    Gson gson = new Gson();
    List<ElementInfo> elementInfoList = null;
    for (File file : fileList) {
      try {
        elementInfoList = gson
            .fromJson(new FileReader(file), elementType);
        elementInfoList.parallelStream()
            .forEach(elementInfo -> elementMapList.put(elementInfo.getKey(), elementInfo));
      } catch (FileNotFoundException e) {
        System.out.println("not found" + e);
      }
    }
  }

  private File[] getFileList(){
    File[] fileList = new File(
        this.getClass().getClassLoader().getResource(DEFAULT_DIRECTORY_PATH).getFile())
        .listFiles(pathname -> !pathname.isDirectory() && pathname.getName().endsWith(".json"));
    if (fileList == null) {
      System.out.println(
          "File Directory Is Not Found! Please Check Directory Location. Default Directory Path = "+
          DEFAULT_DIRECTORY_PATH);
      throw new NullPointerException();
    }
    return fileList;
  }

  public ElementInfo findElementInfoByKey(String key){
    return (ElementInfo) elementMapList.get(key);
  }

  public void saveValue(String key, String value){
    elementMapList.put(key, value);
  }

  public String getValue(String key){
    return elementMapList.get(key).toString();
  }

}
