// 입출금 내역 분석 프로그램
// DD-MM-YYYY, 이동 금액+-, 내역

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "../src/"; // csv 파일 위치 지정

    public static void main(final String... args) throws IOException {
        final Path path = Paths.get(RESOURCES + args[0]);   // path 지정
        final List<String> lines = Files.readAllLines(path);    // 모든 라인 읽어오기
        double total = 0d;      // 합을 double 0으로 초기화
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for(final String line : lines) {    // 한 라인씩 읽어오기
            final String[] columns = line.split(",");   // , 기준으로 스플릿
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);   // 기준에 따른 LocalDate 객체 생성

            // LocalDate 객체의 월(month)를 불러와서 비교 후 해당 값만 total에 계산
            if(date.getMonth() == Month.JANUARY){
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }
        System.out.println("Total amount is " + total); // 출력
    }
}
