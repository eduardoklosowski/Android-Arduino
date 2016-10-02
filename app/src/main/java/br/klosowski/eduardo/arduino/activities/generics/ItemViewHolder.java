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

package br.klosowski.eduardo.arduino.activities.generics;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.klosowski.eduardo.arduino.R;

class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView textName;
    ImageView buttonEdit;
    ImageView buttonDelete;

    ItemViewHolder(View itemView) {
        super(itemView);
        textName = (TextView) itemView.findViewById(R.id.name);
        buttonEdit = (ImageView) itemView.findViewById(R.id.edit);
        buttonDelete = (ImageView) itemView.findViewById(R.id.delete);
    }
}
