package com.alifyz.amazons3sample

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.services.s3.AmazonS3Client
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val REQUEST_RESULT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AWSMobileClient.getInstance().initialize(this).execute()
        pick.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("*/*")
            startActivityForResult(intent, REQUEST_RESULT)
        }
    }


    fun uploadToAmazonS3(filePath : String, file : File) {
        val transferUtility = buildTransferUtility()
        val uploadObserver = transferUtility.upload(filePath, file)


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
        return TransferUtility.builder()
            .context(this.applicationContext)
            .awsConfiguration(AWSMobileClient.getInstance().configuration)
            .s3Client(AmazonS3Client(AWSMobileClient.getInstance().credentialsProvider))
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_RESULT) {
            if(resultCode == Activity.RESULT_OK) {
                val uri = data?.data
                filePreview.setImageURI(uri)
                val filePath = uri?.path
                val file = File(filePath)
                if (filePath != null) {
                    uploadToAmazonS3(filePath, file)
                }
            }
        }
    }


}
