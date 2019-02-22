package clicksource.ir.xmltutorial;

public class SampleGenericClass <T> {
    private T sample;

    public void add(T test){
        sample=test;
    }

    public T get(){
        return sample;
    }
}
