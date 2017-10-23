public class Driver{
    public static void main(String[] args){
	AA a1 = new AA();
	System.out.println("AA aa = new AA :");
	a1.test();

	A a2 = new AA();
	System.out.println("A aa = new AA :");
	a2.test();

	A a3 = new A();
	System.out.println("A aa = new A :");
	a3.test();

	/*
	AA a4 = new A();
	System.out.println("AA aa = new A :");
	a4.test();*/
	
    }
}
