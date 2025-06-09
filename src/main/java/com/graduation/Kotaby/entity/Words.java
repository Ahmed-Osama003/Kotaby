package com.graduation.Kotaby.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "words")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "word_number")
    private int wordNumber;

    @Column(name = "ayah_number")
    private int ayahNumber;

    @Column(name = "sora_number")
    private int soraNumber;

    public Words(int wordNumber, int ayahNumber, int soraNumber) {
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

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(Integer wordNumber) {
        this.wordNumber = wordNumber;
    }

    public int getAyahNumber() {
        return ayahNumber;
    }

    public void setAyahNumber(Integer ayahNumber) {
        this.ayahNumber = ayahNumber;
    }

    public int getSoraNumber() {
        return soraNumber;
    }

    public void setSoraNumber(int soraNumber) {
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
