package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address) {

        //creating body(serialization)
        AddPlace ap = new AddPlace();

        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setName(name);
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setWebsite("http://google.com");

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        ap.setLocation(location);

        List<String> t = new ArrayList<>();
        t.add("dogs park");
        t.add("shop");

        ap.setTypes(t);
        
        return ap;
    }

    public String deletePlacePayload(String placeId) {
        return "{\n" +
                "    \"place_id\":\"" + placeId + "\"\n" +
                "}";
    }
}








