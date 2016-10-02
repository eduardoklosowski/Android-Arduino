/*
 * copyright (c) 2016  eduardo augusto klosowski
 *
 * this program is free software: you can redistribute it and/or modify
 * it under the terms of the gnu general public license as published by
 * the free software foundation, either version 3 of the license, or
 * (at your option) any later version.
 *
 * this program is distributed in the hope that it will be useful,
 * but without any warranty; without even the implied warranty of
 * merchantability or fitness for a particular purpose.  see the
 * gnu general public license for more details.
 *
 * you should have received a copy of the gnu general public license
 * along with this program.  if not, see <http://www.gnu.org/licenses/>.
 */

package br.klosowski.eduardo.arduino.activities.sensor;

import android.content.Context;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.activities.generics.GenericListActivity;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorItemDAO;

public class SensorListActivity extends GenericListActivity<SensorItem> {
    public SensorListActivity() {
        super();
    }

    @Override
    protected int getActivityTitle() {
        return R.string.sensors;
    }

    @Override
    protected Class getFormActivityClass() {
        return SensorFormActivity.class;
    }

    @Override
    protected SensorItemRecyclerAdapter getRecyclerAdapter(Context context,
                                                           List<SensorItem> items) {
        return new SensorItemRecyclerAdapter(context, items);
    }

    @Override
    public SensorItemDAO getItemDAO() {
        return new SensorItemDAO(this);
    }
}
