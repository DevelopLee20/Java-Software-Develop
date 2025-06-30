// 입출금 내역 분석 프로그램
// DD-MM-YYYY, 이동 금액+-, 내역

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "../src/"; // csv 파일 위치 지정

    public static void main(final String... args) throws IOException {
        final Path path = Paths.get(RESOURCES + args[0]);   // path 지정
        final List<String> lines = Files.readAllLines(path);    // 모든 라인 읽어오기
        double total = 0d;      // 합을 double 0으로 초기화

        for(final String line : lines) {    // 한 라인씩 읽어오기
            final String[] columns = line.split(",");   // , 기준으로 스플릿
            final double amount = Double.parseDouble(columns[1]); // double로 파싱
            total += amount;            // 합산
        }
        System.out.println("Total amount is " + total); // 출력
    }
}
