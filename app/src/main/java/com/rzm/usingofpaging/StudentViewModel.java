package com.rzm.usingofpaging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;

import java.util.ArrayList;
import java.util.List;

public class StudentViewModel extends ViewModel {


    private final LiveData<PagedList<Student>> pagedListLiveData;

    public StudentViewModel() {
        pagedListLiveData = new LivePagedListBuilder(new DataSource.Factory() {
            @NonNull
            @Override
            public DataSource create() {
                return new PositionalDataSource() {
                    @Override
                    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
                        Log.d("StudentViewModel", "loadInitial params = " + params.pageSize + " start position = " + params.requestedStartPosition);
                        callback.onResult(getStudents(0, 20), 0, 1000);
                    }

                    @Override
                    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback callback) {
                        Log.d("StudentViewModel", "loadRange params = " + params.loadSize + " start position = " + params.startPosition);
                        callback.onResult(getStudents(params.startPosition, params.loadSize));
                    }
                };
            }
        }, 20).build();
    }

    private List<Student> getStudents(int startPosition, int pageSize) {
        List<Student> list = new ArrayList<>();
        for (int i = startPosition; i < startPosition + pageSize; i++) {
            Student student = new Student();
            student.setId("ID号是:" + i);
            student.setName("我名称:" + i);
            student.setSex("我性别:" + i);
            list.add(student);
        }
        return list;
    }

    public LiveData<PagedList<Student>> getStudent() {
        return pagedListLiveData;
    }
}
