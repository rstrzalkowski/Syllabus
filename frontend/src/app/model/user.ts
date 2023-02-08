export interface User {
  id: number;
  email: string;
  schoolClassName: string
  createdAt: Date;
  updatedAt: Date;
  firstName: string;
  lastName: string;
  personalId: string;
  description?: any;
  child?: any;
  locked: boolean;
  archived: boolean;
  role: string;
}
