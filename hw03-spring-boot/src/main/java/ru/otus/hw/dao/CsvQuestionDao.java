package ru.otus.hw.dao;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        var fileName = fileNameProvider.getTestFileName();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (stream == null) {
                throw new FileNotFoundException(fileName);
            }
            InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);

            var csvReader = new CSVReaderBuilder(streamReader)
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(';')
                            .withIgnoreLeadingWhiteSpace(true)
                            .build())
                    .withSkipLines(1)
                    .build();
            return getQuestionsFromCsv(csvReader);

        } catch (IOException e) {
            throw new QuestionReadException("Cannot read csv", e);
        }
    }

    private List<Question> getQuestionsFromCsv(CSVReader csvReader) {
        CsvToBean<QuestionDto> csvToBean = new CsvToBeanBuilder<QuestionDto>(csvReader)
                .withType(QuestionDto.class)
                .withIgnoreEmptyLine(true)
                .build();
        return csvToBean
                .parse()
                .stream()
                .map(QuestionDto::toDomainObject)
                .toList();
    }
}
