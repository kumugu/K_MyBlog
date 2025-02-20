"use client"

import type React from "react"
import { useEffect, useState } from "react"
import api from "../api/api"
import "../css/Home.css"

interface BlogPost {
  id: number
  title: string
  content: string
  author: string
}

const Home: React.FC = () => {
  const [posts, setPosts] = useState<BlogPost[]>([])

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await api.get<BlogPost[]>("/blogposts")
        setPosts(response.data)
      } catch (error) {
        console.error("Error fetching posts:", error)
      }
    }

    fetchPosts()
  }, [])

  const handleDelete = async (id: number) => {
    try {
      await api.delete(`/blogposts/${id}`)
      setPosts(posts.filter((post) => post.id !== id))
    } catch (error) {
      console.error("Error deleting post:", error)
    }
  }

  return (
    <div className="blog-container">
      <div className="header">
        <h1>Featured Stories</h1>
        <button className="create-button" onClick={() => (window.location.href = "/create")}>
          Create New Post
        </button>
      </div>

      <div className="posts-grid">
        {posts.map((post) => (
          <article key={post.id} className="post-card">
            <div className="post-image">
              <img src={`https://source.unsplash.com/random/400x300?${post.id}`} alt="Post cover" />
            </div>
            <div className="post-content">
              <h2 className="post-title">{post.title}</h2>
              <p className="post-author">Written by {post.author}</p>
              <div className="post-buttons">
                <button className="view-button" onClick={() => (window.location.href = `/post/${post.id}`)}>
                  Read More
                </button>
                <button className="edit-button" onClick={() => (window.location.href = `/edit/${post.id}`)}>
                  Edit
                </button>
                <button className="delete-button" onClick={() => handleDelete(post.id)}>
                  Delete
                </button>
              </div>
            </div>
          </article>
        ))}
      </div>
    </div>
  )
}

export default Home

