package dad.sumader;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class complejo {
	private final DoubleProperty real = new SimpleDoubleProperty();
	private final DoubleProperty imaginario = new SimpleDoubleProperty();

	public complejo() {
	}

	public complejo(double real, double imaginario) {
		setReal(real);
		setImaginario(imaginario);
	}

	public double getReal() {
		return real.get();
	}

	public DoubleProperty realProperty() {
		return real;
	}

	public void setReal(double real) {
		this.real.set(real);
	}

	public double getImaginario() {
		return imaginario.get();
	}

	public DoubleProperty imaginarioProperty() {
		return imaginario;
	}

	public void setImaginario(double imaginario) {
		this.imaginario.set(imaginario);
	}
}
