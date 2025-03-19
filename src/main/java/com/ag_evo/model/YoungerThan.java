package com.ag_evo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.springframework.validation.annotation.Validated;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

@EqualsAndHashCode(callSuper = true)
@Validated
@Data
@AllArgsConstructor
public class YoungerThan extends Policy {

    @Getter
    private static final YoungerThan instance = new YoungerThan();

    @JsonProperty("value")
    private int value;

    private YoungerThan() {
        try{
            Object jsonFile = new JSONParser().parse(new FileReader("src/main/resources/customConfig/youngerThan.json"));
            JSONObject json = (JSONObject) jsonFile;
            this.setId(json.get("id").toString());
            this.setName(json.get("name").toString());
            JSONObject youngerObject = (JSONObject) json.get("youngerThan");
            this.setValue(Integer.parseInt(youngerObject.get("value").toString()));

        } catch(IOException | ParseException e) {
        throw new RuntimeException("Not possible to read youngerThan.json",e);
        }
    }

    public String recalculate(Users user){
        if(Period.between(user.getBirthDate(), LocalDate.now()).getYears() >= value){
            return getId();
        } else {
            return null;
        }
    }
}
