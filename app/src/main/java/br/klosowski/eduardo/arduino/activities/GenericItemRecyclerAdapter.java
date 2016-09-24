package br.klosowski.eduardo.arduino.activities;

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

    GenericItemRecyclerAdapter(Context context, List<I> list) {
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

    public void setOnItemEdited(OnItemEditedListener onItemEdited) {
        mOnItemEdited = onItemEdited;
    }

    public void setOnItemDeleted(OnItemDeletedListener onItemDeleted) {
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

    public interface OnItemEditedListener {
        void onItemEdited(Item item);
    }

    public interface OnItemDeletedListener {
        void onItemDeleted(Item item);
    }
}
