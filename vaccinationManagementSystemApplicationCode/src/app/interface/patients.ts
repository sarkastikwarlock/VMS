export class Patients{
    patientId!: string;
    patientFullname!: string;
    patientEmail!: string;
    patientPassword!: string;
    patientPhone!: string;
    patientDOB!: string;
    patientFirstCentre!: string;
    patientSecondCentre!: string;
    patientFirstTime!: string;
    patientSecondTime!: string;
    patientFirstDate!: string;
    patientSecondDate!: string;
    patientVaccinationType!: string;
    firstDoseIsDone: boolean = false;
    secondDoseIsDone: boolean = false;


    constructor(){}
}