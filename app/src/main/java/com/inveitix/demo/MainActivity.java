package com.inveitix.demo;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.inveitix.demo.data.Note;
import com.inveitix.demo.data.NotesViewModel;
import com.inveitix.demo.databinding.ActivityMainBinding;
import com.inveitix.demo.ui.views.FavoDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;
    private RecyclerView mRecyclerView;
    private NotesAdapter mNotesAdapter;
    private NotesViewModel mViewModel;
    private Button btnAnimate;
    private TextView txtAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViews();
    }

    private void initViews()
    {
        mViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.rec_notes_list);
        mRecyclerView.setLayoutManager(layoutManager);
        mNotesAdapter = new NotesAdapter();
        mRecyclerView.setAdapter(mNotesAdapter);

        mViewModel.getAllNodes().observe(this, new Observer<List<Note>>()
        {
            @Override
            public void onChanged(@Nullable List<Note> notes)
            {
                mNotesAdapter.setData(notes);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mViewModel.insert(new Note(binding.edtExtraContent.getText().toString()));
                binding.edtExtraContent.getText().clear();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mViewModel.deleteAll();
            }
        });

//        Animation fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
//        fadeIn.setDuration(2000);
//
//        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
//        fadeOut.setStartOffset(2000);
//        fadeOut.setDuration(2000);
//
//        final AnimationSet animation = new AnimationSet(false); //change to false
//        animation.addAnimation(fadeIn);
//        animation.addAnimation(fadeOut);

        btnAnimate = findViewById(R.id.btn_animate);
        txtAnimation = findViewById(R.id.txt_animation);
        final ObjectAnimator animation = ObjectAnimator.ofFloat(txtAnimation, "translationY", 10000f);
        animation.setDuration(2000);
        btnAnimate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final FavoDialog dialog = new FavoDialog(MainActivity.this);
                dialog.setTitle("Title VSC");
                dialog.setMessage("Svetovno e bace");
                dialog.setPositiveBtnTitle("Da piem");
                dialog.setNegativeBtnTitle("Stiga veche");
                dialog.setOnNegativeClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        dialog.dismiss();
                    }
                });
                dialog.setOnPositiveClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        dialog.dismiss();
                        AlertDialog.Builder androidDialog = new AlertDialog.Builder(MainActivity.this);
                        androidDialog.setTitle("Da hodim za bira togava ????");
                        androidDialog.setMessage("Lele kakvo chakame");
                        androidDialog.setPositiveButton("Haide", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.dismiss();
                            }
                        });
                        androidDialog.create().show();
                    }
                });
                dialog.show();
            }
        });
    }
}
