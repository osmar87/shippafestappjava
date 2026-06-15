package br.com.gomesdev87.shippafestApi.user;

import br.com.gomesdev87.shippafestApi.utils.Signo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;

@Service
public class SignosServices {

    private final List<Signo> signos = List.of(
            new Signo("Capricórnio", MonthDay.of(Month.DECEMBER, 22), MonthDay.of(Month.JANUARY, 19)),
            new Signo("Aquário", MonthDay.of(Month.JANUARY, 20), MonthDay.of(Month.FEBRUARY, 18)),
            new Signo("Peixes", MonthDay.of(Month.FEBRUARY, 19), MonthDay.of(Month.MARCH, 20)),
            new Signo("Áries", MonthDay.of(Month.MARCH, 21), MonthDay.of(Month.APRIL, 19)),
            new Signo("Touro", MonthDay.of(Month.APRIL, 20), MonthDay.of(Month.MAY, 20)),
            new Signo("Gêmeos", MonthDay.of(Month.MAY, 21), MonthDay.of(Month.JUNE, 21)),
            new Signo("Câncer", MonthDay.of(Month.JUNE, 22), MonthDay.of(Month.JULY, 22)),
            new Signo("Leão", MonthDay.of(Month.JULY, 23), MonthDay.of(Month.AUGUST, 22)),
            new Signo("Virgem", MonthDay.of(Month.AUGUST, 23), MonthDay.of(Month.SEPTEMBER, 22)),
            new Signo("Libra", MonthDay.of(Month.SEPTEMBER, 23), MonthDay.of(Month.OCTOBER, 22)),
            new Signo("Escorpião", MonthDay.of(Month.OCTOBER, 23), MonthDay.of(Month.NOVEMBER, 21)),
            new Signo("Sagitário", MonthDay.of(Month.NOVEMBER, 22), MonthDay.of(Month.DECEMBER, 21))
    );

    public String calcularSigno(LocalDate dataNascimento) {
        MonthDay mesDia = MonthDay.from(dataNascimento);
        return signos.stream()
                .filter(s -> s.pertence(mesDia))
                .map(Signo::nome)
                .findFirst()
                .orElse("Desconhecido");
    }
}