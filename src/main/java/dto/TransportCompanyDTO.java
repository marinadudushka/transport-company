package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransportCompanyDTO {

    private long id;
    private String name;
    private LocalDate foundationDate;
    private BigDecimal initialCapital;

    public TransportCompanyDTO(){

    }

    public TransportCompanyDTO(long id, String name, LocalDate foundationDate, BigDecimal initialCapital) {
        this.id = id;
        this.name = name;
        this.foundationDate = foundationDate;
        this.initialCapital = initialCapital;
    }


}
