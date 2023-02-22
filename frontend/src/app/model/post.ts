export interface PostPage {
  content: Post[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Post {
  postId: number;
  realisationId: number;
  authorId: number;
  title: string;
  content: string;
  subjectName: string;
  authorFirstName: string;
  authorImageUrl: string;
  authorLastName: string;
  createdAt: Date;
  updatedAt: Date;
  edited: boolean;
}
