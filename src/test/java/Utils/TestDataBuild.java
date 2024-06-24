package Utils;

import pojo.AddPlacePojo;
import pojo.Location;
import pojo.deletePlacePojo;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlacePojo addPlace(String name, String language, String address){
        AddPlacePojo p = new AddPlacePojo();
        p.setAccuracy(101);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress(address);
        p.setWebsite("http://OfficeOfFinance.com");
        p.setLanguage(language);

        List<String> types = new ArrayList<>();
        types.add("shoe park1");
        types.add("shop1");

        p.setTypes(types);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);

        p.setLocation(l);
        return p;
    }

    public deletePlacePojo deletePlace(String place_ID){
        deletePlacePojo d = new deletePlacePojo();
        d.setPlace_id(place_ID);
        return d;
    }
}
