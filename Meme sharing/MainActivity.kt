package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {

    var currentImgUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }

    private fun loadMeme(){
        //initializing thr queue request

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"
        val img = findViewById<ImageView>(R.id.memeImageView)
        val bar = findViewById<ProgressBar>(R.id.progressbBar)
        bar.visibility = View.VISIBLE


         // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener{ response ->
                currentImgUrl = response.getString("url")

                Glide.with(this).load(currentImgUrl).listener(object: RequestListener<Drawable>{
                    //helps us to cancel load the progressbar if loading of meme fails
                    override fun onLoadFailed(e: GlideException?,
                                              model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean
                                              ): Boolean {
                           bar.visibility = View.GONE
                        return false
                    }
                    //helps us to cancel load the progressbar if meme loads
                    override fun onResourceReady(resource: Drawable?,
                                                 model: Any?,
                                                 target: Target<Drawable>?,
                                                 dataSource: DataSource?,
                                                 isFirstResource: Boolean
                                               ): Boolean {
                        bar.visibility = View.GONE
                        return false
                    }
                }).into(img)
            },
            Response.ErrorListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            })
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    fun nextMeme(view: View) {
        loadMeme()
    }
    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Hey, checkout this cool meme i got from reddit $currentImgUrl")
        val chooser = Intent.createChooser(intent, "share this meme using ...")
        startActivity(chooser)
    }
}