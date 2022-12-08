package view;

import javafx.beans.value.WritableValue;

public class WritableExecutable implements WritableValue<Executable> {
    @Override
    public Executable getValue() {return ev -> {};}

    @Override
    public void setValue(Executable exe) {

        exe.execute(null);
    }
}