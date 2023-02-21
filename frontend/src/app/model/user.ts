export interface UserPage {
  content: User[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface User {
  id: number;
  createdAt: Date;
  updatedAt: Date;
  email: string;
  firstName: string;
  lastName: string;
  personalId: string;
  description?: any;
  child?: any;
  locked: boolean;
  archived: boolean;
  role: string;
  imageUrl: string;
  schoolClassName?: any;
}
