package com.migueldev.wodwiseapp.domain.exception

class FirestoreAddDocumentException(cause: Throwable? = null) :
    Exception("Error adding document to Firestore", cause)
