export interface PostPage {
  content: Post[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Post {
  postId: number;
  authorId: number;
  title: string;
  content: string;
  authorFirstName: string;
  authorLastName: string;
  createdAt: Date;
  updatedAt: Date;
  edited: boolean;
}
