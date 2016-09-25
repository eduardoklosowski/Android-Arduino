package br.klosowski.eduardo.arduino.activities.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.RunningSensor;
import br.klosowski.eduardo.arduino.models.SensorDirection;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorType;

class MainItemRecyclerAdapter extends RecyclerView.Adapter<MainItemViewHolder> {
    private List<RunningSensor> list;

    MainItemRecyclerAdapter(Context context, List<RunningSensor> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<RunningSensor> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        RunningSensor rSensor = list.get(position);
        SensorItem sensor = rSensor.getSensor();
        long typeId = sensor.getType().getId();
        long directionId = sensor.getDirection().getId();

        if (typeId == SensorType.Digital.getId()) {
            if (directionId == SensorDirection.Input.getId()) {
                return R.layout.item_main_digital_input;
            } else {
                return R.layout.item_main_digital_output;
            }
        } else {
            if (directionId == SensorDirection.Input.getId()) {
                return R.layout.item_main_analogical_input;
            } else {
                return R.layout.item_main_analogical_output;
            }
        }
    }

    @Override
    public MainItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(MainItemViewHolder holder, int position) {
        final RunningSensor rSensor = list.get(position);
        final SensorItem sensor = rSensor.getSensor();
        boolean writable = sensor.getDirection().getId() == SensorDirection.Output.getId();

        holder.textName.setText(sensor.getName());
        if (sensor.getType().getId() == SensorType.Digital.getId()) {
            CheckBox checkValue = (CheckBox) holder.viewValue;
            checkValue.setChecked(rSensor.getValue() == 1);
            if (writable) {
                checkValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox checkValue = (CheckBox) v;
                        int value = 0;
                        if (checkValue.isChecked()) {
                            value = 1;
                        }
                        rSensor.putValue(value);
                    }
                });
            }
        } else {
            EditText editValue = (EditText) holder.viewValue;
            editValue.setText(Integer.toString(rSensor.getValue()));
            if (writable) {
                editValue.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String textValue = s.toString();
                        int value = 0;
                        if (!textValue.isEmpty()) {
                            value = Integer.parseInt(textValue);
                        }
                        rSensor.putValue(value);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        }
    }
}
