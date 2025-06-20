package com.graduation.Kotaby.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HadithResponseDTO {

    @JsonProperty("ahadith")
    private AhadithData ahadith;

    public HadithResponseDTO() {}

    public AhadithData getAhadith() {
        return ahadith;
    }

    public void setAhadith(AhadithData ahadith) {
        this.ahadith = ahadith;
    }

    public static class AhadithData {
        @JsonProperty("result")
        private String result;

        public AhadithData() {}

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}