package com.example.andro.church;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter_Recycler extends RecyclerView.Adapter<MyAdapter_Recycler.MyViewHolder> {
    int Resource;
    Context Con;
    List<Contact> list;
    View view;
    ImageView delete, edite;
    //Handler h = new Handler();
    Animation animation;
    OpenHelperSQLite sqLite;
    EditText name_e, addr_e, phane_e, Father_of_confession_e, Date_of_visit_e, childreen_e;
    AlertDialog al;
    LayoutInflater layoutInflater;
    int color[] = {R.color.red, R.color.yallow, R.color.orange, R.color.green, R.color.nail, R.color.blue, R.color.o};
    Contact n;

    MyAdapter_Recycler(Context context, int resource, List objects) {
        Resource = resource;
        Con = context;
        list = objects;
        sqLite = new OpenHelperSQLite(Con, null, 1);
        animation = AnimationUtils.loadAnimation(Con, R.anim.transport);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View v) {
            super(v);
        }
    }

    TextView name, addres, Date_of_visit;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        layoutInflater = LayoutInflater.from(Con);
        view = layoutInflater.inflate(Resource, parent, false);
        name = view.findViewById(R.id.Name);
        addres = view.findViewById(R.id.addres);
        Date_of_visit = view.findViewById(R.id.Date_of_visit);
        delete = view.findViewById(R.id.delete);
        edite = view.findViewById(R.id.edit);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        name.setText(list.get(position).getName());
        addres.setText(list.get(position).getAddres());
        Date_of_visit.setText(list.get(position).getDate_of_visit());
        holder.itemView.setBackgroundResource(color[position % 7]);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        sqLite.delete(list.get(position));
                        removeItem(position);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                holder.itemView.startAnimation(animation);
            }
        });
        edite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = list.get(position);
                al = new AlertDialog.Builder(Con).setNegativeButton("cansel", null).setPositiveButton("change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        n.setName(name_e.getText().toString());
                        n.setAddres(addr_e.getText().toString());
                        n.setNumberphine(phane_e.getText().toString());
                        n.setFather_of_confession(Father_of_confession_e.getText().toString());
                        n.setDate_of_visit(Date_of_visit_e.getText().toString());
                        n.setChildren(Integer.parseInt(childreen_e.getText().toString()));
                        ((TextView)holder.itemView.findViewById(R.id.Name)).setText(n.getName());
                        ((TextView)holder.itemView.findViewById(R.id.addres)).setText(n.getAddres());
                        ((TextView)holder.itemView.findViewById(R.id.Date_of_visit)).setText(n.getDate_of_visit());
                        sqLite.update(n);
                        updateData(new ArrayList(sqLite.all()));
                    }
                }).setView(layoutInflater.inflate(R.layout.add, null)).create();
                al.show();
                    name_e = al.findViewById(R.id.editText_name);
                    addr_e = al.findViewById(R.id.editText_addr);
                    phane_e = al.findViewById(R.id.editText_phane);
                    Father_of_confession_e = al.findViewById(R.id.editText_Father_of_confession);
                    Date_of_visit_e = al.findViewById(R.id.editText_Date_of_visit);
                    childreen_e = al.findViewById(R.id.editText_childreen);
                    name_e.setText(n.getName());
                    addr_e.setText(n.getAddres());
                    phane_e.setText(n.getNumberphine());
                    Father_of_confession_e.setText(n.getFather_of_confession());
                    Date_of_visit_e.setText(n.getDate_of_visit());
                    childreen_e.setText(n.getChildren() + "");

            }
        });
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void updateData(List viewModels) {
        list.clear();
        list.addAll(viewModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}