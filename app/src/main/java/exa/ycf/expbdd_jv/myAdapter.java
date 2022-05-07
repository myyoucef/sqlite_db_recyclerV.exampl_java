package exa.ycf.expbdd_jv;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

   Context context;
   ArrayList<Etudiant> etuList;

   private int position;

   Animation anim;


   myAdapter(Context c,ArrayList<Etudiant> list){
      context=c;
      etuList=list;
   }

   @Override
   public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_model, parent, false);

      return new myViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
      Etudiant etudiant=etuList.get(position);
      holder.textView1.setText(etudiant.getName());
      holder.textView2.setText(String.valueOf(etudiant.getNote()));
      holder.textView3.setText(String.valueOf(etudiant.getId()));
      holder.imageView.setImageResource(R.drawable.avatar2);

      anim= AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.ani);
      holder.itemView.startAnimation(anim);
   }

   @Override
   public int getItemCount() {
      return etuList.size();
   }

   public int getPosition() {
      return position;
   }
   public void setPosition(int position) {
      this.position = position;
   }

   public class myViewHolder extends RecyclerView.ViewHolder implements
           View.OnCreateContextMenuListener, View.OnLongClickListener{

      TextView textView1;
      TextView textView2;
      TextView textView3;
      ImageView imageView;

      public myViewHolder(@NonNull View itemView) {
         super(itemView);
         textView1 = (TextView) itemView.findViewById(R.id.textView1);
         textView2 = (TextView) itemView.findViewById(R.id.textView2);
         textView3 = (TextView) itemView.findViewById(R.id.textView3);
         imageView = (ImageView) itemView.findViewById(R.id.imageView);
         itemView.setOnCreateContextMenuListener(this);
         itemView.setOnLongClickListener(this);
      }

      public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//         super.onCreateContextMenu(menu, v, menuInfo);
         ((Activity)v.getContext()).getMenuInflater().inflate(R.menu.cmenu,menu);
         menu.setHeaderTitle("Select action : ");
      }

      @Override
      public boolean onLongClick(View view) {
         setPosition(this.getLayoutPosition());
         return false;
      }
   }
}
