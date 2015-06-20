
public class ComplexNumber{
//class Complex Number
  private double real;
  private double imag;
  
public ComplexNumber(double i, double j)
{
    real = i;
    imag = j;
}

public ComplexNumber add(ComplexNumber c2)
{
    return new ComplexNumber(real + c2.getReal(), imag + c2.getImag());
}

public ComplexNumber squared()
{
    return new ComplexNumber(real * real - imag * imag,real * imag *  2.0);
}
public double abs()
{
    return Math.sqrt(real*real + imag*imag);
}

public double getReal()
{
    return real;
}
public double getImag()
{
    return imag;
}

public void setReal(double val)
{
    real = val;
}

public void setImag(double val)
{
    imag = val;
}

  
  
  
};