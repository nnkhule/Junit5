package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GradeCalculatorTest {
    // letterGrade - Ердийн утгууд
    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(95.0);

        assertEquals("A", grade);
    }

    @Test
    @DisplayName("85 оноо B дүн байх ёстой")
    void eightyFiveIsB() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(85.0);

        assertEquals("B", grade);
    }

    @Test
    @DisplayName("75 оноо C дүн байх ёстой")
    void seventyFiveIsC() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(75.0);

        assertEquals("C", grade);
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void sixtyFiveIsD() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(65.0);

        assertEquals("D", grade);
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void thirtyIsF() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(30.0);

        assertEquals("F", grade);
    }


    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(90.0);

        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (A-ийн доод хязгаар)")
    void eightyNinePointNineNineIsB() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(89.99);

        assertEquals("B", grade);
    }

    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой (хязгаарын тохиолдол)")
    void sixtyIsExactlyD() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(60.0);

        assertEquals("D", grade);
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой (D-ийн доод хязгаар)")
    void fiftyNinePointNineNineIsF() {
        GradeCalculator calc = new GradeCalculator();

        String grade = calc.letterGrade(59.99);

        assertEquals("F", grade);
    }
    // Буруу оролтын тест

    @Test
    @DisplayName("-1 оноо өгвөл IllegalArgumentException шидэх ёстой")
    void negativeScoreThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1.0)
        );
    }

    @Test
    @DisplayName("101 оноо өгвөл IllegalArgumentException шидэх ёстой")
    void scoreOver100ThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101.0)
        );
    }
    // totalScore - Зөв утга

    @Test
    @DisplayName("Бүх дээд оноог өгвөл нийт 100 оноо гарах ёстой")
    void totalScoreShouldBe100() {
        GradeCalculator calc = new GradeCalculator();

        double total = calc.totalScore(
                10.0,
                40.0,
                10.0,
                10.0,
                30.0
        );

        assertEquals(100.0, total);
    }

    // totalScore - Буруу утгууд

    @Test
    @DisplayName("Ирц -5 байвал IllegalArgumentException шидэх ёстой")
    void negativeAttendanceThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(
                        -5.0,
                        40.0,
                        10.0,
                        10.0,
                        30.0
                )
        );
    }

    @Test
    @DisplayName("Лабораторийн оноо 41 байвал IllegalArgumentException шидэх ёстой")
    void labOverMaximumThrowsException() {

        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(
                        10.0,
                        41.0,
                        10.0,
                        10.0,
                        30.0
                )
        );
    }

    @ParameterizedTest
    @CsvSource({
            "95, A",
            "90, A",
            "89.99, B",
            "80, B",
            "70, C",
            "60, D",
            "59.99, F",
            "0, F",
            "100, A"
    })
    @DisplayName("Онооны хязгаараас хамаарч зөв үсгэн дүн буцаах")
    void letterGradeBoundaries(double score, String expected) {

        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        String actual = calc.letterGrade(score);

        // Assert
        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "9, 35, 8, 9, 27, 88",
            "10, 30, 10, 10, 25, 85",
            "8, 32, 7, 8, 25, 80",
            "5, 20, 5, 5, 15, 50"
    })
    @DisplayName("Оноонуудын нийлбэрийг зөв тооцох")
    void totalScoreCalculation(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected) {

        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act
        double actual = calc.totalScore(
                att, lab, quiz1, quiz2, exam
        );

        // Assert
        assertEquals(expected, actual);
    }
}