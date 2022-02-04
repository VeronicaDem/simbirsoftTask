package ru.simbirsoft.urltask.db;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Db {
   static Logger log = Logger.getLogger(Db.class.getName());
   public static void saveData(Map<String, Integer> data, String url, LocalDateTime timeOfProcess)  {
       // Use a service account
       Map<String, Object> dataToSave = new HashMap<>();
       dataToSave.put("url", url);
       dataToSave.put("time", timeOfProcess.toString());
       dataToSave.put("words", data);
       InputStream serviceAccount = null;
       try {
           serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");
           GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
           FirebaseOptions options = new FirebaseOptions.Builder()
                   .setCredentials(credentials)
                   .build();
           FirebaseApp.initializeApp(options);

           Firestore db = FirestoreClient.getFirestore();
           ApiFuture<WriteResult> future = db.collection("statistics")
                   .document("FeTsty6OQThLqC6ozdBn").set(dataToSave);
           System.out.println("Update time : " + future.get().getUpdateTime());
       } catch (Exception e) {
           log.error(e);
       }

   }

}
