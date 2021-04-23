package lab4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GaleShapley extends Algorithm {
    private int[][] allPreferences = new int[problema.getHighSchoolListSize() + problema.getStudentListSize()][problema.getStudentListSize()];
    private Map<Student, School> result = new HashMap<>();
    private int[] capacitySchools = new int[problema.getHighSchoolListSize()];
    private int stSize = problema.getStudentListSize();
    private int scSize = problema.getHighSchoolListSize();

    GaleShapley(Problema problema) {
        super(problema);
    }

    @Override
    public Map<Student, School> getSolution() {
        this.studentList = problema.getStudentList();
        this.highSchoolList = problema.getHighSchoolList();
        this.studentPreferences = problema.getStudentPreferences();
        this.highSchoolPreferences = problema.getHighSchoolPreferences();
        makeMatrixWithAllPreferences();
        getStableMariage();

        return result;
    }

    public void makeMatrixWithAllPreferences() {
        /**
         * consideram ca elevii vor fi barbatii si liceele femeile din algoritmul original Gale Shapley
         */
        for (int i = 0; i < this.scSize + this.stSize; i++)
            Arrays.fill(allPreferences[i], -1);

        studentPreferences.entrySet().forEach(entry -> {
            for (int i = 0; i < entry.getValue().size(); i++) {
                allPreferences[studentList.indexOf(entry.getKey())][i] = highSchoolList.indexOf(entry.getValue().get(i));
            }
        });

        highSchoolPreferences.entrySet().forEach(entry -> {
            for (int i = 0; i < entry.getValue().size(); i++) {
                allPreferences[this.stSize + highSchoolList.indexOf(entry.getKey())][i] = studentList.indexOf(entry.getValue().get(i));
            }
        });
        for (int i = 0; i < this.scSize; i++) {
            capacitySchools[i] = highSchoolList.get(i).getCapacity();
        }
    }

    public void getStableMariage() {
        int st1;
        boolean[] stFree = new boolean[this.stSize];
        int[] stRepartization = new int[this.stSize];
        Arrays.fill(stRepartization, -1);
        int freeSt = this.stSize; // toti studentii sunt la inceput nerepartizati
        while (freeSt > 0) { //cat timp mai exista studenti care nu au fost inca repartizati
            //alegem primul student care nu a fost repartizat inca
            for (st1 = 0; st1 < this.stSize; st1++) {
                if (!stFree[st1]) {
                    break;
                }
            }
            for (int i = 0; i < this.scSize && !stFree[st1]; i++) {
                int sc = allPreferences[st1][i];
                if (capacitySchools[sc] > 0) {
                    stRepartization[st1] = sc;
                    capacitySchools[sc]--;
                    stFree[st1] = true;
                    freeSt--;
                }
                else {
                    //daca liceul la care isi doreste studentul nu mai are locuri
                    //vedem care sunt studentii care au fost repartizati la liceul pe care acesta il doreste
                    // si daca sunt mai slab cotati decat el, vom face schimbarea
                    for(int st2=0;st2<this.stSize;st2++){
                        if(stRepartization[st2]==sc){
                            if(scPrefersStudent1OverStudent2(sc,st1,st2)==true){
                                stRepartization[st1]=sc;
                                stRepartization[st2]=-1;
                                stFree[st1]=true;
                                stFree[st2]=false;
                                break;
                            }
                        }
                }
            }
        }
    }
    for(int i=0;i<stSize;i++){
        result.put(studentList.get(i),highSchoolList.get(stRepartization[i]));
    }
}

    public boolean scPrefersStudent1OverStudent2(int school, int student1, int student2) {
        for(int st = 0;st<this.stSize;st++){
            if(allPreferences[this.stSize+school][st]==student1)
                return true;
            if(allPreferences[this.stSize+school][st]==student2)
                return false;
        }
        return true;
    }
}
