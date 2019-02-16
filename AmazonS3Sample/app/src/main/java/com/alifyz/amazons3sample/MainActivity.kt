package com.alifyz.amazons3sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.services.s3.AmazonS3Client
import com.jaiselrahman.filepicker.activity.FilePickerActivity
import com.jaiselrahman.filepicker.model.MediaFile
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private val REQUEST_RESULT = 100
    private val BUCKET_NAME = "sbucket-userfiles-mobilehub-2069569018"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AWSMobileClient.getInstance().initialize(this).execute()
        pick.setOnClickListener {

            val intent = Intent(this, FilePickerActivity::class.java)
            startActivityForResult(intent, REQUEST_RESULT)
        }
    }


    fun uploadToAmazonS3(mediaFile: MediaFile) {
        val transferUtility = buildTransferUtility()
        val file = File(mediaFile.path)
        val uploadObserver = transferUtility.upload(BUCKET_NAME,mediaFile.name, file)

        uploadObserver.setTransferListener(object : TransferListener {
            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                Log.i("Progress: ", bytesCurrent.toString())
            }

            override fun onStateChanged(id: Int, state: TransferState?) {
                if(state == TransferState.COMPLETED) {
                    Toast.makeText(applicationContext, "Transfer Completed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(id: Int, ex: Exception?) {
                Toast.makeText(applicationContext, ex?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun buildTransferUtility(): TransferUtility {

        val credentials = BasicAWSCredentials("KEY",
                                "SECRET_KEY")

        return TransferUtility.builder()
            .context(this.applicationContext)
            .awsConfiguration(AWSMobileClient.getInstance().configuration)
            .s3Client(AmazonS3Client(credentials))
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_RESULT) {
            if(resultCode == Activity.RESULT_OK) {
                val extras = data?.extras
                val listFiles = extras?.getParcelableArrayList<MediaFile>(FilePickerActivity.MEDIA_FILES)
                listFiles?.get(0)?.let { uploadToAmazonS3(it) }
            }
        }
    }


}
