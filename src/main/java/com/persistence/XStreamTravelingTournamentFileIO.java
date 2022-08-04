package com.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;

import org.optaplanner.persistence.xstream.impl.domain.solution.XStreamSolutionFileIO;

import com.domain.TravelingTournament;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.persistence.model.TournamentData;

public class XStreamTravelingTournamentFileIO extends XStreamSolutionFileIO<TravelingTournament> {
    /*
     * public XStreamTravelingTournamentFileIO() {
     * super(TravelingTournament.class);
     * super.xStream.setMode(XStream.NO_REFERENCES);
     * }
     */

    @Override
    public TravelingTournament read(File inputSolutionFile) {
        return read(inputSolutionFile, false);
    }

    public TravelingTournament read(File inputSolutionFile, Boolean ignoreDaysAssignment) {

        try (Reader reader = new InputStreamReader(new FileInputStream(inputSolutionFile), "UTF-8")) {

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            
          
            TournamentData tournamentData = xmlMapper.readValue(reader, TournamentData.class);

            TransformViewToSolution transformViewToSolution = new TransformViewToSolution();
            return transformViewToSolution.convert(tournamentData, ignoreDaysAssignment);

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed reading inputSolutionFile (" + inputSolutionFile + ").", e);
        }
    }

    @Override
    public void write(TravelingTournament solution, File outputSolutionFile) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputSolutionFile), "UTF-8")) {

            TransformSolutionToView transformSolutionToView = new TransformSolutionToView();
            Boolean getOnlyResultData = false;
            TournamentData tournamentData = transformSolutionToView.convert(solution, getOnlyResultData);

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            xmlMapper.writeValue(writer, tournamentData);

        } catch (IOException e) {
            throw new IllegalArgumentException("Failed writing outputSolutionFile (" + outputSolutionFile + ").", e);
        }
    }

}
