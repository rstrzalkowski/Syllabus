export interface PostPage {
  content: Post[],
  totalPages: number
}

export interface Post {
  postId: number;
  authorId: number;
  content: string;
  authorFirstName: string;
  authorLastName: string;
  createdAt: Date;
  updatedAt: Date;
  edited: boolean;
}
