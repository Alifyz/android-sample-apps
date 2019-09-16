# Alarm Manager API - Sample

# TL;DR

This project aims to use the Amazon Web Services Platform to upload a simple image into a S3 Bucket.

```kotlin
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
```


# Pre-requisites

  - Android Studio 2.3+
  - Android SDK Build Tools 23+
  - Target SDK (19) - Most of them. 
 
# Contact Info

- Email: alifyz@outlook.com
- Twitter: @AlifyzPires


License
----

MIT

Copyright - 2018

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

