package data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.webinar.bawp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Course;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder>
implements View.OnClickListener{
    //private ArrayList<Course> courseArrayList;
    private CourseData courseData = new CourseData();
    public OnItemClickListener itemClickListener;


    @NonNull
    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_row, parent, false);

        return new ViewHolder(courseRow);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Context context = holder.courseTitle.getContext();

        Course course = courseData.courseList().get(position);
        holder.courseTitle.setText(course.getCourseName());

        Picasso.with(context)
                .load(course.getImageResourseId(context))
                .into(holder.courseImageView);

        Picasso.with(holder.courseTitle.getContext())
                .load(course.getImageResourseId(context))
                .into(holder.authorImageView);

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(), course.getImageResourseId(context));
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int bgColor = palette.getMutedColor(ContextCompat.getColor(context,
                        android.R.color.black));
                holder.courseTitle.setBackgroundColor(bgColor);
                holder.authorImageView.setBorderColor(bgColor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseData.courseList().size();

    }
    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView courseTitle;
        public ImageView courseImageView;
        public CircleImageView authorImageView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            itemView.setOnClickListener(this);

            courseTitle = itemView.findViewById(R.id.courseTitleId);
            courseImageView = itemView.findViewById(R.id.courseimageId);
            authorImageView = itemView.findViewById(R.id.authorimageID);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());

        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }
}
