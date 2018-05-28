package com.example.diu.bangladictionary;

import java.lang.ref.SoftReference;
import java.util.MissingFormatArgumentException;

public class ReportCard {
    private String name;
    private String numericalAnalysisGrade;
    private String MISGrad;
    private String OOPGrade;
    private String AIGrade;

    public ReportCard(String name,String numericalAnalysisGrade,String MISGrad,String OOPGrade,String AIGrade){
        this.name=name;
        this.numericalAnalysisGrade=numericalAnalysisGrade;
        this.MISGrad= MISGrad;
        this.OOPGrade=OOPGrade;
        this.AIGrade=AIGrade;
    }

    public String getName(){
        return name;
    }
    public String getNumericalAnalysisGrade(){
        return numericalAnalysisGrade;
    }

    public String getMISGrad() {
        return MISGrad;
    }

    public String getOOPGrade() {
        return OOPGrade;
    }

    public String getAIGrade() {
        return AIGrade;
    }

    @Override
    public String toString() {
        String report="Name:"+name+";"+"NumericalAnalysisGrade:"+";"+numericalAnalysisGrade+";"+"MISGrade:"+MISGrad+";"+"OOPGrade:"+OOPGrade+";"+"AIGrade:"+AIGrade+";";
        return report;
    }
}
