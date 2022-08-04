package com.persistence.helper;

import java.io.BufferedReader;
import java.io.FileReader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.persistence.model.TournamentData;

public class TournamentHelper {

    public static String readFromFile(String filePath) throws Exception {
        StringBuilder aStringBuffer = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while (br.ready())
            aStringBuffer.append(br.readLine());
        br.close();

        return aStringBuffer.toString();
    }

    public static TournamentData parseTournamentData(String xmlString) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());

        return xmlMapper.readValue(xmlString, TournamentData.class);
    }

    public static TournamentData getTournamentData(String filePath) throws Exception {
        return parseTournamentData(readFromFile(filePath));
    }
}
