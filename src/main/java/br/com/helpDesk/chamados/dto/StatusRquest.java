package br.com.helpDesk.chamados.dto;

import java.util.Objects;

public class StatusRquest {


    private String status;

    public StatusRquest() {
    }

    public StatusRquest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        StatusRquest that = (StatusRquest) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }
}
