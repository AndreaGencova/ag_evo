package com.ag_evo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.validation.annotation.Validated;

import java.io.FileReader;
import java.io.IOException;

@EqualsAndHashCode(callSuper = true)
@Validated
@Data
@AllArgsConstructor
public class IsMemberOf extends Policy {

    @Getter
    private static final IsMemberOf instance = new IsMemberOf();

    @JsonProperty("value")
    private String value;

    private IsMemberOf() {
        try{
            Object jsonFile = new JSONParser().parse(new FileReader("src/main/resources/customConfig/isMemberOf.json"));
            JSONObject json = (JSONObject) jsonFile;
            this.setId(json.get("id").toString());
            this.setName(json.get("name").toString());
            JSONObject memberObject = (JSONObject) json.get("isMemberOf");
            this.setValue(memberObject.get("value").toString());

        } catch(IOException | ParseException e) {
        throw new RuntimeException("Not possible to read isMemberOf.json",e);
        }
    }

    public String recalculate(Users user){
        if(user.getOrgUnits().stream().anyMatch(org -> org.equals(value))){
            return getId();
        } else {
            return null;
        }
    }
}
