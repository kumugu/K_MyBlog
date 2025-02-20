import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/api";
import "../css/BlogPostDetail.css"; // 스타일을 적용할 CSS 파일

// BlogPost 데이터의 타입을 정의
interface BlogPost {
  id: number;
  title: string;
  content: string;
  author: string;
}

const BlogPostDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>(); // useParams로부터 id를 문자열로 가져오기
  const [post, setPost] = useState<BlogPost | null>(null); // 초기 상태는 null로 설정

  useEffect(() => {
    const fetchPost = async () => {
      try {
        if (id) {
          const response = await api.get(`/blogposts/${id}`);
          setPost(response.data);
        }
      } catch (error) {
        console.error("Error fetching post:", error);
      }
    };

    fetchPost();
  }, [id]);

  return (
    <div className="blogpost-detail-container">
      {post ? (
        <div className="post-detail">
          <h1 className="post-title">{post.title}</h1>
          <p className="post-author"><strong>Author:</strong> {post.author}</p>
          <div className="post-content">
            <p>{post.content}</p>
          </div>
        </div>
      ) : (
        <p className="loading-text">Loading...</p>
      )}
    </div>
  );
};

export default BlogPostDetail;
