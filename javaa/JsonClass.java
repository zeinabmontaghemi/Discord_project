package javaa;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;

public class JsonClass {

    final String path = "D:\\MidtermProject-master (1)\\MidtermProject-master\\src\\src\\main\\java.jsondata.json";
    JsonClass jsonObject;

    void convertstrtoJson(String id , String password ,String phoneNumber , String email , String countrycode){
        jsonObject = new JSONObject();
        jsonObject.addProperty("Id" , id);
        jsonObject.addProperty("Password", password);
        jsonObject.addProperty("PhoneNumber", phoneNumber );
        jsonObject.addProperty("Email", email);
        jsonObject.addProperty("countrycode", countrycode);
        writejsontoFile();
    }

    void writejsontoFile(){
        try (PrintWriter out = new PrintWriter(new File(path)) ){
            out.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JSONArray readjsonFile() {
        try {
            File file = new File(path);
            JsonParser parser = new JsonParser();
            FileReader fileReader = new FileReader(file);
            JsonArray array = new JsonArray();
            array = (JsonArray) parser.parse(fileReader);
            return array;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
