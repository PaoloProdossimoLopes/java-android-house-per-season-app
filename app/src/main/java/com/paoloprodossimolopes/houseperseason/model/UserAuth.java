package com.paoloprodossimolopes.houseperseason.model;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.paoloprodossimolopes.houseperseason.helper.FirebaseHelper;

public class UserAuth {

    //Properties
    private final String id;
    private String name;
    private String phone;
    private String email;
    private String password;

    //Constructor
    public UserAuth() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        this.id = reference.push().getKey();
    }

    //Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    @Exclude public String getPassword() { return password; }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    @Exclude public void setPassword(String password) { this.password = password; }

    //Helpers
    private void saveMeOnDatabase() {
        DatabaseReference reference = FirebaseHelper.getDatabaseReference().child("users").child(getId());
        reference.setValue(this);
    }

    public void registerMeOnFirebaseAuth(CompletionLambda completion) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                email, password
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                saveMeOnDatabase();
                completion.completion(true, null);
            } else {
                String error = task.getException().getMessage();
                completion.completion(false, error);
            }
        });
    }

    @FunctionalInterface
    public interface CompletionLambda {
        void completion(@NonNull boolean isSuccessful, @Nullable String errorMessage);
    }

    /* NOTE:
     * Exclude nao deixa o fireabase salvar essa propriedade, é como se ela fosse ignorada
     */
}

