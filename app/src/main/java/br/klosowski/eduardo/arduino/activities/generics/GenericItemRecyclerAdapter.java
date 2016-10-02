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

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.klosowski.eduardo.arduino.ConfirmDeleteDialog;
import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.Item;
import br.klosowski.eduardo.arduino.models.ItemDAO;

public abstract class GenericItemRecyclerAdapter<I extends Item>
        extends RecyclerView.Adapter<ItemViewHolder> {
    private Context context;
    private List<I> list;
    private ItemDAO<I> itemDAO;
    private OnItemEditedListener mOnItemEdited;
    private OnItemDeletedListener mOnItemDeleted;

    public GenericItemRecyclerAdapter(Context context, List<I> list) {
        this.context = context;
        this.list = list;
        itemDAO = getItemDAO(context);
    }

    abstract protected ItemDAO<I> getItemDAO(Context context);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<I> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    void setOnItemEdited(OnItemEditedListener onItemEdited) {
        mOnItemEdited = onItemEdited;
    }

    void setOnItemDeleted(OnItemDeletedListener onItemDeleted) {
        mOnItemDeleted = onItemDeleted;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_edit_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final I item = list.get(position);

        holder.textName.setText(item.getName());
        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemEdited != null) {
                    mOnItemEdited.onItemEdited(item);
                }
            }
        });
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmDeleteDialog(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemDAO.delete(item);
                        if (mOnItemDeleted != null) {
                            mOnItemDeleted.onItemDeleted(item);
                        }
                    }
                }).make().show();
            }
        });
    }

    interface OnItemEditedListener {
        void onItemEdited(Item item);
    }

    interface OnItemDeletedListener {
        void onItemDeleted(Item item);
    }
}
