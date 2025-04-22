package com.graduation.Kotaby.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Words")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "word_number")
    private Integer wordNumber;

    @Column(name = "ayah_number")
    private Integer ayahNumber;

    @Column(name = "sora_number")
    private Integer soraNumber;

    public Words(Integer wordNumber, Integer ayahNumber, Integer soraNumber) {
        this.wordNumber = wordNumber;
        this.ayahNumber = ayahNumber;
        this.soraNumber = soraNumber;
    }

    public Words() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(Integer wordNumber) {
        this.wordNumber = wordNumber;
    }

    public Integer getAyahNumber() {
        return ayahNumber;
    }

    public void setAyahNumber(Integer ayahNumber) {
        this.ayahNumber = ayahNumber;
    }

    public Integer getSoraNumber() {
        return soraNumber;
    }

    public void setSoraNumber(Integer soraNumber) {
        this.soraNumber = soraNumber;
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", wordNumber=" + wordNumber +
                ", ayahNumber=" + ayahNumber +
                ", soraNumber=" + soraNumber +
                '}';
    }
}
